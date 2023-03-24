package com.example.calculatorapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.R
import com.example.calculatorapp.data.InputItems
import com.example.calculatorapp.data.StaticData
import com.example.calculatorapp.data.performCalculations
import com.example.calculatorapp.ui.AppViewModelProvider

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState = viewModel.homeUiState

    Column() {
        CalculationDisplay(
            uiState = homeUiState,
            onValueChange = {
                val outputValue = performCalculations(it.fieldValue)
                viewModel.updateUiState(it.copy(outputValue = outputValue))
            },
            modifier = modifier.fillMaxWidth()
        )
        CalculationInput(
            onDeleteClicked = {
                val fieldValue = homeUiState.fieldValue

                if (fieldValue.isNotEmpty()) {
                    val newFieldValue = fieldValue.substring(
                        0,
                        fieldValue.length - 1
                    )

                    val outputValue = performCalculations(newFieldValue)
                    viewModel.updateUiState(
                        homeUiState.copy(
                            fieldValue = newFieldValue,
                            outputValue = outputValue
                        )
                    )
                }
            },
            onButtonClicked = {
                value ->
                when (value) {
                    InputItems.Clear -> {
                        viewModel.clearInputOutputText()
                    }
                    InputItems.Equal -> {
                        if (homeUiState.outputValue.isNotEmpty()) {
                            viewModel.updateUiState(
                                homeUiState.copy(
                                    fieldValue = homeUiState.outputValue,
                                    outputValue = "",
                                )
                            )
                        }
                    }
                    else -> {
                        val newFieldValue = homeUiState.fieldValue + value.label
                        val outputValue = performCalculations(newFieldValue)

                        viewModel.updateUiState(
                            homeUiState.copy(
                                fieldValue = newFieldValue,
                                outputValue = outputValue
                            )
                        )
                    }
                }
            },
        )
    }
}

@Composable
fun CalculationDisplay(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onValueChange: (HomeUiState) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    // To Do : Disable keyboard
    Column() {
        TextField(
            value = uiState.fieldValue,
            onValueChange = { onValueChange(uiState.copy(fieldValue = it)) },
            singleLine = true,
            textStyle = TextStyle.Default.copy(
                fontSize = 24.sp,
                textAlign = TextAlign.End
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = uiState.outputValue,
            fontSize = 36.sp,
            textAlign = TextAlign.Right,
            modifier = modifier.padding(end = 16.dp)
        )
    }
}

@Composable
fun CalculationInput(
    modifier: Modifier = Modifier,
    onDeleteClicked: () -> Unit = {},
    onButtonClicked: (InputItems) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        StaticData.inputSigns.forEach { row ->
            CalculationInputButtonRow(
                items = row,
                onDeleteClicked = onDeleteClicked,
                onButtonClicked = onButtonClicked,
            )
        }
    }
}

@Composable
fun CalculationInputButtonRow(
    items: List<InputItems>,
    onDeleteClicked: () -> Unit,
    onButtonClicked: (InputItems) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach {
            item -> CalculationInputButton(
                inputItems = item,
                onDeleteClicked = onDeleteClicked,
                onButtonClicked = onButtonClicked,
                modifier = modifier
                    .weight(1f)
                    .height(IntrinsicSize.Max)
            )
        }
    }
}

@Composable
fun CalculationInputButton(
    inputItems: InputItems,
    onDeleteClicked: () -> Unit,
    onButtonClicked: (InputItems) -> Unit,
    modifier: Modifier = Modifier
) {
    if(inputItems == InputItems.Delete) {
        OutlinedButton(
            onClick = onDeleteClicked,
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_backspace_24),
                contentDescription = stringResource(id = inputItems.labelId),
            )
        }
    } else {
        OutlinedButton(
            onClick = { onButtonClicked(inputItems) },
            modifier = modifier
        ) {
            Text(
                text = stringResource(id = inputItems.labelId),
                fontSize = 24.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculationDisplay() {
    CalculationDisplay(uiState = HomeUiState())
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculationInput() {
    CalculationInput()
}