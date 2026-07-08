package com.app.compose.ui.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.app.compose.ui.view.AppTextField

private fun validateUsername(username: String): String? = when {
    username.isBlank() -> "Username cannot be empty"
    !username.matches(Regex("^[0-9]{10}$")) -> "Enter a valid username"
    else -> null
}

private fun validatePassword(password: String): String? = when {
    password.isBlank() -> "Password cannot be empty"
    password.length < 6 -> "Password must be at least 6 characters"
    else -> null
}

@Composable
fun LoginScreen() {

    var username by remember {
        mutableStateOf("")
    }
    var usernameError by remember { mutableStateOf<String?>(null) }

    var password by remember {
        mutableStateOf("")
    }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.background(color = Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AppTextField(
                value = username,
                onValueChange = { newValue ->
                    username = newValue
                    usernameError = validateUsername(newValue)
                },
                label = "Enter username",
                keyboardType = KeyboardType.Text,
                title = "Username",
                isError = usernameError != null,
                errorMessage = usernameError,
                maxLength = 10
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppTextField(
                value = password,
                onValueChange = { newValue ->
                    password = newValue
                    passwordError = validatePassword(newValue)
                },
                label = "Enter password",
                keyboardType = KeyboardType.Password,
                title = "Password",
                isError = passwordError != null,
                errorMessage = passwordError,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = {
                    passwordVisible = !passwordVisible
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            CompositionLocalProvider(LocalRippleConfiguration provides null) {
                TextButton(
                    onClick = {

                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {

                    Text("Forgot Password?")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)

            ) {

                Text("Login")

            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account? ")
                CompositionLocalProvider(LocalRippleConfiguration provides null) {
                    TextButton(
                        onClick = {

                        },
                        contentPadding = PaddingValues(horizontal = 4.dp)
                    ) {
                        Text("Sign Up")
                    }
                }

            }
        }
    }

}