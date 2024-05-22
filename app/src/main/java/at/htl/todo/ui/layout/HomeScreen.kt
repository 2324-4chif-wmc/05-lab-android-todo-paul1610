package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore

@Composable
fun Home(model: Model, store: ModelStore) {
    Column(modifier = Modifier.padding(top = 70.dp, start = 20.dp)) {
        Text(text = model.username, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        
        Column(modifier = Modifier.padding(top = 15.dp)) {
            Text(text = "${model.todos.size} ToDos have been loaded!", fontSize = 15.sp)
            Text(text = "${(model.todos.filter { todo -> todo.completed }).size } " +
                    "ToDos have been completed!", fontSize = 15.sp)
        }
        
        Column(modifier = Modifier.padding(top = 30.dp)) {
            AddTodo(model = model, store = store)
        }
    }
}

@Composable
fun AddTodo(model: Model, store: ModelStore) {
    var text by remember { mutableStateOf("") }

    Text(text = "Enter a new ToDo here:", fontWeight = FontWeight.Bold)
    TextField(
        value = text,
        label = { Text(text = "Your Task") },
        onValueChange = { text = it }
    )
    Button(onClick = { store.addTodo(text); text = "" }) {
        Text(text = "Submit")
    }
}