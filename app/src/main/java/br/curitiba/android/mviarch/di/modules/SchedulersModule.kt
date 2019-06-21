package br.curitiba.android.mviarch.di.modules

import br.curitiba.android.mviarch.di.qualifiers.IOScheduler
import br.curitiba.android.mviarch.di.qualifiers.UIScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulersModule {

    @Provides
    @UIScheduler
    fun bindUIScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @IOScheduler
    fun bindIOScheduler(): Scheduler {
        return Schedulers.io()
    }
}