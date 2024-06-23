package com.gyub.design.component.textfield

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gyub.design.R
import com.gyub.design.theme.PuumInTypography

/**
 * 배경이 있는 입력 TextField
 *
 * @author   Gyub
 * @created  2024/06/19
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedInputTextField(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Gray,
    textStyle: TextStyle = PuumInTypography.bodyLarge,
    value: String = "",
    placeholder: String = "",
    placeholderTextStyle: TextStyle = PuumInTypography.bodyLarge.copy(color = Color.DarkGray),
    @DrawableRes icon: Int = 0,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            singleLine = true,
            visualTransformation = visualTransformation
        ) { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxSize()
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = placeholderTextStyle
                    )
                }

                if (icon != 0) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    )
                }

                innerTextField()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoundedInputTextFieldPreview() {
    var contents by remember { mutableStateOf("") }

    RoundedInputTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        value = contents,
        icon = R.drawable.ico_password_show_30x22,
        placeholder = "placeHolder",
        onValueChange = { contents = it }
    )
}