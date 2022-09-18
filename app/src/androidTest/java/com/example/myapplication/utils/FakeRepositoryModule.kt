package com.example.myapplication.utils

import com.example.myapplication.di.RepositoryModule
import com.example.myapplication.model.News
import com.example.myapplication.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)

class FakeRepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository(): NewsRepository =
        object : NewsRepository {
            val news = arrayListOf(
                News(
                    "Tesla to build the world's biggest CCS-compatible Supercharger locations with Magic Docks",
                    "After being snubbed by Texas for EV charger subsidies, Tesla managed to win the proposed US$6.4 million in grants from the California Energy Commission for building four Supercharger locations, three… [+1935 chars]",
                    "Daniel Zlatev",
                    "https://www.notebookcheck.net/Tesla-to-build-the-world-s-biggest-CCS-compatible-Supercharger-locations-with-Magic-Docks.649468.0.html",
                    "https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/tesla_superchargers.jpg"
                ),
                News(
                    "Tesla’s battery supplier in China is hanging by a thread, with a ‘factory bubble’ the only way it’s still working",
                    "After being snubbed by Texas for EV charger subsidies, Tesla managed to win the proposed US$6.4 million in grants from the California Energy Commission for building four Supercharger locations, three… [+1935 chars]",
                    "Emma O'Brien, Bloomberg",
                    "https://fortune.com/2022/09/10/tesla-battery-supplier-in-china-factory-bubble-amid-covid-lockdown/",
                    "https://content.fortune.com/wp-content/uploads/2022/09/Tesla-battery-maker-COVID-lockdown-factory-bubble-GettyImages-1393761091-e1662827711417.jpg?resize=1200,600",
                )
            )

            override suspend fun getNews(country:String): List<News> = news
            override  fun getNew(title:String): News = news[0]
        }
}