package fr.epita.assistants.scheduler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyTask<INPUT_TYPE, RETURN_TYPE> implements Task<RETURN_TYPE> {

    public CompletableFuture<RETURN_TYPE> myFuture;

    public MyTask(CompletableFuture<RETURN_TYPE> myFuture) {
        this.myFuture = myFuture;
    }

    public static <RETURN_TYPE> Task<RETURN_TYPE> of(Supplier<RETURN_TYPE> actionSupplier) {
        CompletableFuture<RETURN_TYPE> tmp = CompletableFuture.supplyAsync(actionSupplier);
        return new MyTask<>(tmp);
    }

    @Override
    public CompletableFuture<RETURN_TYPE> build() {
        return this.myFuture;
    }

    @Override
    public Task<RETURN_TYPE> onErrorRecoverWith(Function<Throwable, RETURN_TYPE> recoveryFunction) {
        myFuture = myFuture.exceptionally(recoveryFunction);
        return this;
    }

    @Override
    public <NEW_RETURN_TYPE> Task<NEW_RETURN_TYPE> andThenDo(Function<RETURN_TYPE, NEW_RETURN_TYPE> action) {
        CompletableFuture<NEW_RETURN_TYPE> res = myFuture.thenApply(action);
        return new MyTask<>(res);
    }

    @Override
    public Task<RETURN_TYPE> andThenWait(long number, TimeUnit timeUnit) {
        CompletableFuture<RETURN_TYPE> res = myFuture.thenApplyAsync(Function.identity(), CompletableFuture.delayedExecutor(number, timeUnit));
        return new MyTask<>(res);
    }
}
