package com.example.albimrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import com.example.albimrestaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var totalValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurando o listener para o botão de finalizar
        binding.buttonButtonFinal.setOnClickListener {
            var isAnyItemSelected = false

            // Verifica se alguma caixa de seleção está marcada
            if (binding.checkAppetizerOption1.isChecked ||
                binding.checkAppetizerOption2.isChecked ||
                binding.checkAppetizerOption3.isChecked ||
                binding.checkAppetizerOption4.isChecked ||
                binding.checkMainDishesOption1.isChecked ||
                binding.checkMainDishesOption2.isChecked ||
                binding.checkMainDishesOption3.isChecked ||
                binding.checkMainDishesOption4.isChecked ||
                binding.checkDrinksOption1.isChecked ||
                binding.checkDrinksOption2.isChecked ||
                binding.checkDessertsOption1.isChecked ||
                binding.checkDessertsOption2.isChecked ||
                binding.checkDessertsOption3.isChecked ||
                binding.checkDessertsOption4.isChecked
            ) {
                isAnyItemSelected = true
            }

            // Exibe a mensagem apropriada
            if (isAnyItemSelected) {
                showNotify("Seu pedido foi enviado para o balcão do restaurante")
            } else {
                showNotify("Você deve selecionar pelo menos um produto")
            }
        }

        // Configurando os listeners para as caixas de seleção
        setupCheckBoxListeners()
    }

    // Exibe uma notificação
    private fun showNotify(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    // Configura um listener para uma caixa de seleção específica
    private fun setupCheckBoxListener(checkBox: CheckBox, price: String) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            // Extrai o preço da string e atualiza o valor total conforme necessário
            val priceFloat = price.substring(3).replace(",", ".").toFloat()
            totalValue += if (isChecked) priceFloat else -priceFloat
            updateTotalValue()
        }
    }

    // Configura os listeners para todas as caixas de seleção
    private fun setupCheckBoxListeners() {
        setupCheckBoxListener(binding.checkAppetizerOption1, getString(R.string.Appetizer_option1_price))
        setupCheckBoxListener(binding.checkAppetizerOption2, getString(R.string.Appetizer_option2_price))
        setupCheckBoxListener(binding.checkAppetizerOption3, getString(R.string.Appetizer_option3_price))
        setupCheckBoxListener(binding.checkAppetizerOption4, getString(R.string.Appetizer_option4_price))
        setupCheckBoxListener(binding.checkMainDishesOption1, getString(R.string.main_dishes_option1_price))
        setupCheckBoxListener(binding.checkMainDishesOption2, getString(R.string.main_dishes_option2_price))
        setupCheckBoxListener(binding.checkMainDishesOption3, getString(R.string.main_dishes_option3_price))
        setupCheckBoxListener(binding.checkMainDishesOption4, getString(R.string.main_dishes_option4_price))
        setupCheckBoxListener(binding.checkDrinksOption1, getString(R.string.drinks_option1_price))
        setupCheckBoxListener(binding.checkDrinksOption2, getString(R.string.drinks_option2_price))
        setupCheckBoxListener(binding.checkDessertsOption1, getString(R.string.desserts_option1_price))
        setupCheckBoxListener(binding.checkDessertsOption2, getString(R.string.desserts_option2_price))
        setupCheckBoxListener(binding.checkDessertsOption3, getString(R.string.desserts_option3_price))
        setupCheckBoxListener(binding.checkDessertsOption4, getString(R.string.desserts_option4_price))
    }

    // Atualiza o valor total exibido na tela
    private fun updateTotalValue() {
        binding.textTotalValue.text = "R$ %.2f".format(totalValue)
    }
}
