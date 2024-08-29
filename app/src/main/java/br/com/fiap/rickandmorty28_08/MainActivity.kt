package br.com.fiap.rickandmorty28_08

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.rickandmorty28_08.model.Character
import br.com.fiap.rickandmorty28_08.model.Result
import br.com.fiap.rickandmorty28_08.service.RetrofitFactory
import br.com.fiap.rickandmorty28_08.ui.theme.RickAndMorty2808Theme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //enableEdgeToEdge()
    setContent {
      RickAndMorty2808Theme {
        //CharacterDetails()
        CharacterList()
      }
    }
  }
}

@Composable
fun CharacterList(modifier: Modifier = Modifier) {

  var characterList by remember {
    mutableStateOf(listOf<Character>())
  }

  val callCharacterList = RetrofitFactory()
    .getCharacterService()
    .getCharacterList()

  callCharacterList.enqueue(object : Callback<Result>{
    override fun onResponse(p0: Call<Result>, p1: Response<Result>) {
      characterList = p1.body()!!.results!!
    }

    override fun onFailure(p0: Call<Result>, p1: Throwable) {

    }

  })

  LazyColumn(
    modifier = Modifier.padding(16.dp)
  ) {

    items(characterList){ character ->
      Card(
        onClick = { },
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 4.dp)
      ) {
        Row {
          Card(
            modifier = Modifier.size(60.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Magenta)
          ) {
            AsyncImage(model = character.image, contentDescription = "")
          }
          Column(
            modifier = Modifier.padding(8.dp)
          ) {
            Text(text = character.charName)
            Text(text = character.species)
          }
        }
      }
    }

  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CharacterListPreview() {
  CharacterList()
}

@Composable
fun CharacterDetails() {

  var id by remember {
    mutableStateOf("")
  }

  var character by remember {
    mutableStateOf(Character())
  }

  Column(
    modifier = Modifier.padding(24.dp)
  ) {
    OutlinedTextField(
      value = id,
      onValueChange = { valorDigitado ->
        id = valorDigitado
      })
    Button(onClick = {

      val callCharacter = RetrofitFactory()
        .getCharacterService()
        .getCharacterById(id.toInt())

      callCharacter.enqueue(object : Callback<Character>{
        override fun onResponse(p0: Call<Character>, response: Response<Character>) {
          character = response.body()!!
        }

        override fun onFailure(p0: Call<Character>, p1: Throwable) {

        }

      })

    }) {
      Text(text = "Buscar personamgem")
    }
    Text(text = "ID: ${character.id}")
    Text(text = "Nome: ${character.charName}")
    Text(text = "Espécie: ${character.species}")
    Text(text = "Origem: ${character.origin?.name}")
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CharacterDetailsPreview() {
  CharacterDetails()
}


