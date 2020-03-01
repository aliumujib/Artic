package com.aliumujib.artic.ui.inject.module.presentation



//@Module
//abstract class PresentationModule {
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ArticleListViewModel::class)
//    abstract fun bindArticleListViewModel(viewModel: ArticleListViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailsViewModel::class)
//    abstract fun binDetailsViewModel(viewModel: DetailsViewModel): ViewModel
//
//
//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
//
//}
//
//
//@MustBeDocumented
//@Target(AnnotationTarget.FUNCTION)
//@Retention(AnnotationRetention.RUNTIME)
//@MapKey
//annotation class ViewModelKey(val value: KClass<out ViewModel>)