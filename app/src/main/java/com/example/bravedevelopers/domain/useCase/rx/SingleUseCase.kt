package com.example.bravedevelopers.domain.useCase.rx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<T> :UseCase(){
    internal abstract fun buildUseCaseSingle():Single<T>

fun execute(
    onSuccess:((t:T)->Unit)
)
{
   val disposable=buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess)

   disposable?.let { compositeDisposable.add(it) }


}

}