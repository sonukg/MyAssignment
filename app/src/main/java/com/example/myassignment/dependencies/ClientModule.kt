package com.example.myassignment.dependencies

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ClientModule {

    /*@Provides
    @Singleton
    fun provideGithubIssueRepository(retrofit: Retrofit, db: DemoAppDatabase): GithubIssueRepository {
        return GithubIssueRepository(retrofit, db)
    }

    @Provides
    @Singleton
    fun provideGithubCommentRepository(retrofit: Retrofit, db: DemoAppDatabase): GithubIssueCommentRepository {
        return GithubIssueCommentRepository(retrofit, db)
    }*/
}
