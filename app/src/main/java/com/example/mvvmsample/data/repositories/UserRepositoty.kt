package com.example.mvvmsample.data.repositories
import com.example.mvvmsample.data.network.MyApi
import com.example.mvvmsample.data.network.SafeApiException
import com.example.mvvmsample.data.network.model.AuthResponse
import retrofit2.Response

class UserRepositoty:SafeApiException() {
    suspend fun userLOgin(email:String,password:String):AuthResponse{

        return apiRequest { MyApi().userLogin(email!!,password!!) }
        //return MyApi().userLogin(email!!,password!!)
        /*   val loginResponse = MutableLiveData<String>()

      simple
       MyApi().userLogin(email,password)
             .enqueue(object : retrofit2.Callback<ResponseBody>{
                 override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                     loginResponse.value = t.message
                 }

                 override fun onResponse(
                     call: Call<ResponseBody>,
                     response: Response<ResponseBody>
                 ) {
                     if(response.isSuccessful){
                         loginResponse.value = response.body()?.string()
                     }else{
                         loginResponse.value = response.errorBody()?.string()
                     }
                  }

             })
        return loginResponse*/
    }
}