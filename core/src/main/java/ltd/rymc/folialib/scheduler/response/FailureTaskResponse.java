/*
 * MIT License
 *
 * Copyright (c) 2022 Fairy Project
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ltd.rymc.folialib.scheduler.response;

import lombok.RequiredArgsConstructor;
import ltd.rymc.folialib.scheduler.TaskState;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
public class FailureTaskResponse<R> implements TaskResponse<R> {

    private final Throwable throwable;
    private final String errorMessage;

    @Override
    public TaskState getState() {
        return TaskState.FAILURE;
    }

    @Override
    public @Nullable Throwable getThrowable() {
        return throwable;
    }

    @Override
    public @Nullable String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public @Nullable R getResult() {
        return null;
    }
}
