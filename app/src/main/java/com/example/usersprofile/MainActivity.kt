package com.example.usersprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            UserView()
        }

    }
}

@Composable
fun UserView() {
    // Компоненты для автоматической рекомпозиции элементов (по mutableStateOf, через by и делегирование свойства)
    var nameSM by remember { mutableStateOf(TextFieldValue("")) }
    var ageSM by remember { mutableStateOf(TextFieldValue("")) }
    var genderSM by remember { mutableStateOf("Male") }
    var isSubscribedSM by remember { mutableStateOf(false) }
    var showResultSM by remember { mutableStateOf(false) }

    // Располагаем элементы вертикально
    Column(
        modifier = Modifier.fillMaxSize().padding(40.dp),
        // Модификаторы компонента:
        // fillMaxSize() - растягивает компонент по всей длине и ширине контейнера
        // padding(40.dp) - устанавливает одно значение для отступов от всех четырех сторон
        // На будущее про dp - https://startandroid.ru/ru/11-pamjatka/40-edinitsy-izmerenija-chem-otlichaetsja-dp-dip-ot-px-screen-density.html
        verticalArrangement = Arrangement.Center, // Ровняем по центру
        horizontalAlignment = Alignment.Start // Выравнивание по левому краю
    ) {
        // Имя
        Text(stringResource(R.string.name_label))
        Spacer(modifier = Modifier.height(10.dp))
        // Поле ввода имени
        TextField(
            value = nameSM.text,  // представляет введенное в текстовое поле значение в виде строки
            onValueChange = { nameSM = TextFieldValue(it) },  // Функция обработки изменения введенного значения
            modifier = Modifier.fillMaxWidth()  // Максимальная ширина
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Возраст
        Text(stringResource(R.string.age_label))
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = ageSM.text,
            onValueChange = { ageSM = TextFieldValue(it) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Пол
        Text(stringResource(R.string.gender_label))
        Spacer(modifier = Modifier.width(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = genderSM == "Male",
                onClick = { genderSM = "Male" }
            )
            Text(stringResource(R.string.gender_option_male))

            Spacer(modifier = Modifier.width(20.dp))

            RadioButton(
                selected = genderSM == "Female",
                onClick = { genderSM = "Female" }
            )
            Text(stringResource(R.string.gender_option_female))
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Подписка на рассылку
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isSubscribedSM,
                onCheckedChange = { isSubscribedSM = it }
            )
            Text(stringResource(R.string.subscribe_label))
        }

        Spacer(modifier = Modifier.height(80.dp))

        // Кнопка (блокируется если нет имени)
        Button(
            onClick = { showResultSM = true },
            enabled = nameSM.text.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.submit_button))
        }

        Spacer(modifier = Modifier.height(40.dp))

        val finalString = "${stringResource(R.string.name_label)}: ${nameSM.text}\n" +
        "${stringResource(R.string.age_label)}: ${ageSM.text}\n" +
        "${stringResource(R.string.gender_label)}: ${if (genderSM == "Male") stringResource(R.string.gender_option_male)
         else stringResource(R.string.gender_option_female)}\n" +
         "${stringResource(R.string.subscription_label)}: ${if (isSubscribedSM) "Да" else "Нет"}"

        // Итоговое сообщение
        if (showResultSM) {
            Text(
                text = finalString
            )
        }
    }
}