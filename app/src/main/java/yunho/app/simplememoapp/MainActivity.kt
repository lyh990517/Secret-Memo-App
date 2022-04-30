package yunho.app.simplememoapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import yunho.app.simplememoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var changePW :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUnlockButton()
        initChangePasswordButton()
    }
    private fun initUnlockButton(){
        binding.unlockButton.setOnClickListener {
            if(changePW){
                return@setOnClickListener
            }
            //getSharedPreference 사용
            val password = getSharedPreferences("password", Context.MODE_PRIVATE)
            val inputPassword = binding.passwordBar.text.toString()
            if(inputPassword.equals(password.getString("password","0000"))){
                Toast.makeText(this,"메모화면으로 이동합니다",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MemoActivity::class.java)
                startActivity(intent)
            }else
            {
                Toast.makeText(this,"비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initChangePasswordButton(){
        binding.changePasswordButton.setOnClickListener {
            val inputPassword = binding.passwordBar.text.toString()
            val password = getSharedPreferences("password",Context.MODE_PRIVATE)
            if(changePW){
                password.edit {
                    putString("password",inputPassword)
                    commit()
                }
                changePW = false
                binding.changePasswordButton.setBackgroundColor(Color.GRAY)
            }else{
                if(inputPassword.equals(password.getString("password","0000"))){
                    changePW = true
                    binding.passwordBar.text.clear()
                    binding.changePasswordButton.setBackgroundColor(Color.RED)
                    Toast.makeText(this,"원하시는 번호를 입력후에 버튼을 한번더 눌러주세요",Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}
//TODO Room Database 이용해서 메모를 저장하기
//TODO RecyclerView를 이용해서 저장한 메모들을 리스트형태로 보여주기
