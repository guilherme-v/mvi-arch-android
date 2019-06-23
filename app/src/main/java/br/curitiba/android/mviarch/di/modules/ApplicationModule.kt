package br.curitiba.android.mviarch.di.modules

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import br.curitiba.android.mviarch.Database
import br.curitiba.android.mviarch.di.qualifiers.ApplicationContext
import br.curitiba.android.mviarch.di.scopes.ApplicationScope
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    fun bindContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @ApplicationScope
    fun provideDatabaseHelper(@ApplicationContext context: Context): SupportSQLiteOpenHelper {
        val config = SupportSQLiteOpenHelper.Configuration.builder(context)
            .name("mvi_arch.db")
            .callback(object : SupportSQLiteOpenHelper.Callback(1) {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    val driver = AndroidSqliteDriver(db)
                    Database.Schema.create(driver)
                }

                override fun onUpgrade(db: SupportSQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                }
            })
            .build()

        return FrameworkSQLiteOpenHelperFactory().create(config)
    }

    @Provides
    @ApplicationScope
    fun provideDatabase(helper: SupportSQLiteOpenHelper): Database {
        return Database(AndroidSqliteDriver(helper))
    }
}