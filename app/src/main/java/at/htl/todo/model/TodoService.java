package at.htl.todo.model;


import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.todo.util.Config;
import at.htl.todo.util.resteasy.RestApiClientBuilder;

@Singleton
public class TodoService {
    static final String TAG = TodoService.class.getSimpleName();
    public final TodoClient todoClient;
    public final ModelStore store;

    @Inject
    TodoService(RestApiClientBuilder builder, ModelStore store) {
        var url = Config.getProperty("json.placeholder.baseurl");
        Log.i(TAG, "Creating TodoService with base url: " + url);
        todoClient = builder.build(TodoClient.class, url);
        this.store = store;
    }
    public void getAll() {
        CompletableFuture
                .supplyAsync(todoClient::all)
                .thenAccept(store::setTodos)
                .exceptionally((e) -> {
                    Log.e(TAG, "Error loading todos", e);
                    return null;
                });
    }
}