package at.htl.todo.ui.layout

import Todos
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore

@Composable
fun TabScreen(model: Model, store: ModelStore) {
    val tabs = listOf("Home", "Todos")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = model.tabState) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier.height(45.dp),
                    selected = index == model.tabState,
                    onClick = { store.switchTabState(index) },
                    icon = {
                        when(index) {
                            0 -> Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            1 -> Icon(imageVector = Icons.Default.List, contentDescription = null)
                        }
                    }
                )
            };
        }
    }

    when(model.tabState) {
        0 -> Home(model = model, store = store)
        1 -> Todos(model = model, modifier = Modifier.padding(all = 32.dp), store)
    }
}