package com.example.todoist.common.di

import com.example.todoist.data.remote.TodoistRDS
import com.example.todoist.data.remote.infrastructure.TokenizeInterceptor
import com.example.todoist.presentation.scene.main.MainActivity
import com.example.todoist.presentation.scene.projects.ProjectsFragment
import com.pacoworks.rxpaper2.RxPaperBook
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: ProjectsFragment)
}

@Module
class ApplicationModule {

    @Provides
    fun converterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun adapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    fun okHttpClient(tokenizeInterceptor: TokenizeInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(tokenizeInterceptor)
            .build()

    @Provides
    fun retrofit(
        converterFactory: GsonConverterFactory,
        adapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.todoist.com/rest/v1/")
        .addCallAdapterFactory(adapterFactory)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    fun todoistRDS(retrofit: Retrofit) =
        retrofit.create(TodoistRDS::class.java)

    @Provides
    fun paperBook(): RxPaperBook = RxPaperBook.with()

    @Provides
    @MainScheduler
    fun mainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @IOScheduler
    fun ioScheduler(): Scheduler = Schedulers.io()
}