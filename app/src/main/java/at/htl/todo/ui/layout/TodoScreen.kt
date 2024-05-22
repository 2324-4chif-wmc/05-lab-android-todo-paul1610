import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.Todo

@Composable
fun Todos(model: Model, modifier: Modifier = Modifier, store: ModelStore) {
    val todos = model.todos
    LazyColumn(
        modifier = modifier.padding(16.dp)
    ) {
        items(todos.size) { index ->
            TodoRow(todo  = todos[index], store = store)
            HorizontalDivider()
        }
    }
}

@Composable
fun TodoRow(todo: Todo, store: ModelStore) {
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