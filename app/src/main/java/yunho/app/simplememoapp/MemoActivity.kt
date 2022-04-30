package yunho.app.simplememoapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import yunho.app.simplememoapp.databinding.ActivityMemoBinding

class MemoActivity:AppCompatActivity() {
    private val binding by lazy {
        ActivityMemoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val content = getSharedPreferences("memo", MODE_PRIVATE)
        binding.memo.setText(content.getString("memo","hello"))
        binding.memo.addTextChangedListener {
            content.edit {
                putString("memo",binding.memo.text.toString())
            }
        }
        binding.clearButton.setOnClickListener {
            binding.memo.text.clear()
        }
    }
}