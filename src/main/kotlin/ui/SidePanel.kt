package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import utils.handCursor

@Composable
fun SidePanel(onMenuSelected: (header:String) -> Unit, onNewsSearched: (searchedText: String,header: String) -> Unit) {
    var searchedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(0.15f).fillMaxHeight().padding(12.dp).background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap = useResource("logo_image.png") {
            loadImageBitmap(it)
        }

        Image(bitmap,"Logo", modifier = Modifier.width(100.dp))
        Spacer(modifier = Modifier.padding(18.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            singleLine = true,
            placeholder = {
                Text("Search")
            },
            value = searchedText,
            onValueChange = {
                searchedText = it
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onNewsSearched(searchedText,"Results for '$searchedText'")
                    },
                    modifier = Modifier.size(40.dp).pointerHoverIcon(handCursor())
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button",
                        tint = Color.Black
                    )
                }
            }
        )
        TextButton(
            onClick = {
                searchedText = ""
                onMenuSelected("Headlines")
            }
        ) {
            Text(
                "Headlines",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.pointerHoverIcon(handCursor())
            )
        }

    }
}