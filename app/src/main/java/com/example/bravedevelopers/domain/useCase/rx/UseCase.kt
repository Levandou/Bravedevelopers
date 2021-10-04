package com.example.bravedevelopers.domain.useCase.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

 open class UseCase {
    var disposable: Disposable? = null
    val compositeDisposable = CompositeDisposable()

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed){
                it.dispose()
            }
        }
    }

    fun clear(){
        compositeDisposable.clear()
        }


}