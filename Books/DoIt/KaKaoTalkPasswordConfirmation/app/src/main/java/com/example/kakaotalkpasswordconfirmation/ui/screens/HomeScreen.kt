package com.example.kakaotalkpasswordconfirmation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kakaotalkpasswordconfirmation.R

@Composable
fun HomeScreen() {
    var passwordValue by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.main_desc),
            fontSize = 17.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "khkevin2@gmail.com",
            color = Color(0xFFCFCFCE),
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = passwordValue,
            onValueChange = { passwordValue = it },
            placeholder = { Text(text = "비밀번호") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(id = R.string.password_txt))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "확인")
        }
    }
}