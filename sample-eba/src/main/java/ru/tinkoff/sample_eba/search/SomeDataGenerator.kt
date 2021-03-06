package ru.tinkoff.sample_eba.search

import io.reactivex.Observable

class SomeDataGenerator(private val someDataList: MutableList<SearchResult>) :
        (Int) -> Observable<List<SearchResult>> {
    override fun invoke(numberOfGeneratedObjects: Int): Observable<List<SearchResult>> {
        return if (numberOfGeneratedObjects !in 0..5000) Observable.error(
            IllegalArgumentException("number should be greater than zero")
        )
        else {
            Observable.fromCallable {
                someDataList.apply {
                    repeat(numberOfGeneratedObjects) {
                        add(SearchResult("some text#$it"))
                    }
                }.toList()
            }
        }
    }
}