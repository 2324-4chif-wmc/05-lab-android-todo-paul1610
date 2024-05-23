import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.Todo

@Composable
fun Todos(model: Model, modifier: Modifier = Modifier, store: ModelStore) {
    val todos = model.todos
    if(model.editTodo == null) {
        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            items(todos.size) { index ->
                TodoRow(todo  = todos[index], store = store)
                HorizontalDivider()
            }
        }
    } else {
        EditTodo(todo = model.editTodo, store = store)
    }
}

@Composable
fun EditTodo(todo: Todo, store: ModelStore) {
    val editTitle = remember { mutableStateOf(false) }
    var newTitle = remember { mutableStateOf(todo.title) }

    Column(modifier = Modifier.padding(top = 80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        if (editTitle.value) {
            TextField(value = newTitle.value, onValueChange = { newTitle.value = it })
            Column(
                modifier = Modifier.padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { editTitle.value = false; store.setTodoTitle(todo, newTitle.value) }) {
                    Text(text = "Done")
                }
            }
        } else {
            Text(text = newTitle.value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Column(
                modifier = Modifier.padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { editTitle.value = true }) {
                    Text(text = "Edit")
                }
                Button(onClick = { store.setEditTodo(null) }) {
                    Text(text = "Go back")
                }
            }
        }
    }
}

@Composable
fun TodoRow(todo: Todo, store: ModelStore) {
    Surface (
        onClick = { store.setEditTodo(todo) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = todo.id.toString(),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.weight(1f))
            Checkbox(
                checked = todo.completed,
                onCheckedChange = { store.setTodoDone(todo) }
            )
        }
    }
}