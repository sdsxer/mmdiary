/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sdsxer.mmdiary.tasks.domain.usecase;

import android.support.annotation.NonNull;

import com.sdsxer.mmdiary.UseCase;
import com.sdsxer.mmdiary.data.source.TasksRepository;
import com.sdsxer.mmdiary.tasks.domain.usecase.ActivateTask.RequestValues;
import com.sdsxer.mmdiary.tasks.domain.usecase.ActivateTask.ResponseValue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Marks a task as active (not completed yet).
 */
public class ActivateTask extends UseCase<RequestValues, ResponseValue> {

    private final TasksRepository mTasksRepository;

    public ActivateTask(@NonNull TasksRepository tasksRepository) {
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        String activeTask = values.getActivateTask();
        mTasksRepository.activateTask(activeTask);
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static final class RequestValues implements com.sdsxer.mmdiary.UseCase.RequestValues {

        private final String mActivateTask;

        public RequestValues(@NonNull String activateTask) {
            mActivateTask = checkNotNull(activateTask, "activateTask cannot be null!");
        }

        public String getActivateTask() {
            return mActivateTask;
        }
    }

    public static final class ResponseValue implements com.sdsxer.mmdiary.UseCase.ResponseValue { }
}
