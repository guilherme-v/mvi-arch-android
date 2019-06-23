package br.curitiba.android.mviarch.di.modules

import br.curitiba.android.mviarch.data.ProjectsRepository
import com.nhaarman.mockito_kotlin.whenever
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import org.mockito.Mockito

@Module
class TestDataModule {

    @Provides
    fun projectRepository(): ProjectsRepository {
        val mock = Mockito.mock(ProjectsRepository::class.java)
        whenever(mock.getProjects()).thenReturn(Single.just(emptyList()))
        return mock
    }
}
