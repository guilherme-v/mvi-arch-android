package br.curitiba.android.mviarch.util

import br.curitiba.android.mviarch.data.models.Project

object FakeFactory {

    fun randomProject(): Project {
        return Project(String.random(), String.random(), String.random(), Int.random(), String.random(), String.random(),
            String.random(), Boolean.random())
    }
}