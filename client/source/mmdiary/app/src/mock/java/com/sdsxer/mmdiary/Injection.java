/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.sdsxer.mmdiary;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Context;
import android.support.annotation.NonNull;
import com.sdsxer.mmdiary.addedittask.domain.usecase.DeleteTask;
import com.sdsxer.mmdiary.addedittask.domain.usecase.GetTask;
import com.sdsxer.mmdiary.addedittask.domain.usecase.SaveTask;
import com.sdsxer.mmdiary.data.FakeTasksRemoteDataSource;
import com.sdsxer.mmdiary.data.source.TasksRepository;
import com.sdsxer.mmdiary.data.source.local.TasksLocalDataSource;
import com.sdsxer.mmdiary.data.source.local.ToDoDatabase;
import com.sdsxer.mmdiary.statistics.domain.usecase.GetStatistics;
import com.sdsxer.mmdiary.tasks.domain.filter.FilterFactory;
import com.sdsxer.mmdiary.tasks.domain.usecase.ActivateTask;
import com.sdsxer.mmdiary.tasks.domain.usecase.ClearCompleteTasks;
import com.sdsxer.mmdiary.tasks.domain.usecase.CompleteTask;
import com.sdsxer.mmdiary.tasks.domain.usecase.GetTasks;
import com.sdsxer.mmdiary.data.source.TasksDataSource;
import com.sdsxer.mmdiary.util.AppExecutors;

/**
 * Enables injection of mock implementations for
 * {@link TasksDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        ToDoDatabase database = ToDoDatabase.getInstance(context);
        return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(new AppExecutors(),
                        database.taskDao()));
    }

    public static GetTasks provideGetTasks(@NonNull Context context) {
        return new GetTasks(provideTasksRepository(context), new FilterFactory());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    public static GetTask provideGetTask(@NonNull Context context) {
        return new GetTask(Injection.provideTasksRepository(context));
    }

    public static SaveTask provideSaveTask(@NonNull Context context) {
        return new SaveTask(Injection.provideTasksRepository(context));
    }

    public static CompleteTask provideCompleteTasks(@NonNull Context context) {
        return new CompleteTask(Injection.provideTasksRepository(context));
    }

    public static ActivateTask provideActivateTask(@NonNull Context context) {
        return new ActivateTask(Injection.provideTasksRepository(context));
    }

    public static ClearCompleteTasks provideClearCompleteTasks(@NonNull Context context) {
        return new ClearCompleteTasks(Injection.provideTasksRepository(context));
    }

    public static DeleteTask provideDeleteTask(@NonNull Context context) {
        return new DeleteTask(Injection.provideTasksRepository(context));
    }

    public static GetStatistics provideGetStatistics(@NonNull Context context) {
        return new GetStatistics(Injection.provideTasksRepository(context));
    }
}
