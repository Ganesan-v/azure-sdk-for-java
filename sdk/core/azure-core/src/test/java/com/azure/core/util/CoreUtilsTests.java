// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoreUtilsTests {
    private static final byte[] BYTES = "Hello world!".getBytes(StandardCharsets.UTF_8);

    private static final byte[] UTF_8_BOM = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
    private static final byte[] UTF_16BE_BOM = {(byte) 0xFE, (byte) 0xFF};
    private static final byte[] UTF_16LE_BOM = {(byte) 0xFF, (byte) 0xFE};
    private static final byte[] UTF_32BE_BOM = {(byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF};
    private static final byte[] UTF_32LE_BOM = {(byte) 0xFF, (byte) 0xFE, (byte) 0x00, (byte) 0x00};

    @Test
    public void findFirstOfTypeEmptyArgs() {
        assertNull(CoreUtils.findFirstOfType(null, Integer.class));
    }

    @Test
    public void findFirstOfTypeWithOneOfType() {
        int expected = 1;
        Object[] args = { "string", expected };
        Integer actual = CoreUtils.findFirstOfType(args, Integer.class);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findFirstOfTypeWithMultipleOfType() {
        int expected = 1;
        Object[] args = { "string", expected, 10 };
        Integer actual = CoreUtils.findFirstOfType(args, Integer.class);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findFirstOfTypeWithNoneOfType() {
        Object[] args = { "string", "anotherString" };
        assertNull(CoreUtils.findFirstOfType(args, Integer.class));
    }

    @Test
    public void testProperties() {
        assertNotNull(CoreUtils.getProperties("azure-core.properties").get("version"));
        assertNotNull(CoreUtils.getProperties("azure-core.properties").get("name"));
        assertTrue(CoreUtils.getProperties("azure-core.properties").get("version")
            .matches("\\d+\\.\\d+\\.\\d+(-beta\\.\\d+)?"));
    }

    @Test
    public void testMissingProperties() {
        assertNotNull(CoreUtils.getProperties("foo.properties"));
        assertTrue(CoreUtils.getProperties("foo.properties").isEmpty());
        assertNull(CoreUtils.getProperties("azure-core.properties").get("foo"));
    }

    @ParameterizedTest
    @MethodSource("bomAwareToStringSupplier")
    public void bomAwareToString(byte[] bytes, String contentType, String expected) {
        assertEquals(expected, CoreUtils.bomAwareToString(bytes, contentType));
    }

    private static Stream<Arguments> bomAwareToStringSupplier() {
        return Stream.of(
            Arguments.arguments(null, null, null),
            Arguments.arguments(BYTES, null, new String(BYTES, StandardCharsets.UTF_8)),
            Arguments.arguments(BYTES, "charset=UTF-16BE", new String(BYTES, StandardCharsets.UTF_16BE)),
            Arguments.arguments(BYTES, "charset=invalid", new String(BYTES, StandardCharsets.UTF_8)),
            Arguments.arguments(addBom(UTF_8_BOM), null, new String(BYTES, StandardCharsets.UTF_8)),
            Arguments.arguments(addBom(UTF_16BE_BOM), null, new String(BYTES, StandardCharsets.UTF_16BE)),
            Arguments.arguments(addBom(UTF_16LE_BOM), null, new String(BYTES, StandardCharsets.UTF_16LE)),
            Arguments.arguments(addBom(UTF_32BE_BOM), null, new String(BYTES, Charset.forName("UTF-32BE"))),
            Arguments.arguments(addBom(UTF_32LE_BOM), null, new String(BYTES, Charset.forName("UTF-32LE"))),
            Arguments.arguments(addBom(UTF_8_BOM), "charset=UTF-8", new String(BYTES, StandardCharsets.UTF_8)),
            Arguments.arguments(addBom(UTF_8_BOM), "charset=UTF-16BE", new String(BYTES, StandardCharsets.UTF_8))
        );
    }

    private static byte[] addBom(byte[] arr1) {
        byte[] mergedArray = new byte[arr1.length + BYTES.length];

        System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
        System.arraycopy(BYTES, 0, mergedArray, arr1.length, BYTES.length);

        return mergedArray;
    }
}
