package at.htl.todo.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Comparator;

import javax.inject.Inject;
import javax.inject.Singleton;
import at.htl.todo.util.store.Store;

@Singleton
public class ModelStore extends Store<Model> {

    @Inject
    ModelStore() {
        super(Model.class, new Model());
    }

    public void setTodos(Todo[] todos) {
        apply(model -> model.todos = todos);
    }
    public void setTodoDone(Todo todo) {
        apply(model -> model.todos[todo.id.intValue() - 1].completed = !todo.completed);
    }

    public void addTodo(String content) {
        apply(model -> {
            var newTodo = new Todo(1L, Arrays.stream(model.todos).max(Comparator.comparingLong(o -> o.id)).get().id + 1, content, false);
            var newTodos = Arrays.copyOf(model.todos, model.todos.length + 1);
            newTodos[newTodos.length - 1] = newTodo;

            model.todos = newTodos;
        });
    }

    public void switchTabState(int index) {
        apply(model -> model.tabState = index);
    }
}