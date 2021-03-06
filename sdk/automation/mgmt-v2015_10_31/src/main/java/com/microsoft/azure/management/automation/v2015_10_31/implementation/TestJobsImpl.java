/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * abc
 */

package com.microsoft.azure.management.automation.v2015_10_31.implementation;

import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.automation.v2015_10_31.TestJobs;
import rx.Completable;
import rx.functions.Func1;
import rx.Observable;
import com.microsoft.azure.management.automation.v2015_10_31.TestJob;
import com.microsoft.azure.management.automation.v2015_10_31.TestJobCreateParameters;

class TestJobsImpl extends WrapperImpl<TestJobsInner> implements TestJobs {
    private final AutomationManager manager;

    TestJobsImpl(AutomationManager manager) {
        super(manager.inner().testJobs());
        this.manager = manager;
    }

    public AutomationManager manager() {
        return this.manager;
    }

    @Override
    public Observable<TestJob> createAsync(String resourceGroupName, String automationAccountName, String runbookName, TestJobCreateParameters parameters) {
        TestJobsInner client = this.inner();
        return client.createAsync(resourceGroupName, automationAccountName, runbookName, parameters)
        .map(new Func1<TestJobInner, TestJob>() {
            @Override
            public TestJob call(TestJobInner inner) {
                return new TestJobImpl(inner, manager());
            }
        });
    }

    @Override
    public Observable<TestJob> getAsync(String resourceGroupName, String automationAccountName, String runbookName) {
        TestJobsInner client = this.inner();
        return client.getAsync(resourceGroupName, automationAccountName, runbookName)
        .map(new Func1<TestJobInner, TestJob>() {
            @Override
            public TestJob call(TestJobInner inner) {
                return new TestJobImpl(inner, manager());
            }
        });
    }

    @Override
    public Completable resumeAsync(String resourceGroupName, String automationAccountName, String runbookName) {
        TestJobsInner client = this.inner();
        return client.resumeAsync(resourceGroupName, automationAccountName, runbookName).toCompletable();
    }

    @Override
    public Completable stopAsync(String resourceGroupName, String automationAccountName, String runbookName) {
        TestJobsInner client = this.inner();
        return client.stopAsync(resourceGroupName, automationAccountName, runbookName).toCompletable();
    }

    @Override
    public Completable suspendAsync(String resourceGroupName, String automationAccountName, String runbookName) {
        TestJobsInner client = this.inner();
        return client.suspendAsync(resourceGroupName, automationAccountName, runbookName).toCompletable();
    }

}
