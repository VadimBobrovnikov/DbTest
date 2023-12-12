package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//здесь реализуется экран бд, внесение данных, удаление данных и т.д.

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseViewScreen(db: MainDB, modifier: Modifier = Modifier) {
    var itemName by remember { mutableStateOf("") }
    var itemTelNum by remember { mutableStateOf("") }

    val items: List<Item> by db.getDao().getAllItems().collectAsState(emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    label = { Text("Name") },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    )
                )

                OutlinedTextField(
                    value = itemTelNum,
                    onValueChange = { itemTelNum = it },
                    label = { Text("Code") },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        if (itemName.isNotBlank() && itemTelNum.isNotBlank()) {
                            val newItem = Item(name = itemName, tel_num = itemTelNum)
                            CoroutineScope(Dispatchers.IO).launch {
                                db.getDao().insertItem(newItem)
                            }
                            // Clear input fields after saving
                            itemName = ""
                            itemTelNum = ""
                        }
                    },
                    modifier = Modifier
                        .padding(end = 4.dp)
                ) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
                    Text("Save")
                }

                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            db.getDao().deleteAllItems()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete All")
                    Text("Delete All")
                }
            }

            Text("Database Content:")
            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Id: ${item.id}",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Name: ${item.name}",
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Tel/Code: ${item.tel_num}",
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                db.getDao().deleteItem(item)
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        Text("Delete")
                    }
                }
            }
        }
    }

}