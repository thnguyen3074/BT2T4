package com.example.bt2t4.data

import retrofit2.http.GET

interface ApiService {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): ApiResponse<List<Task>>
}
