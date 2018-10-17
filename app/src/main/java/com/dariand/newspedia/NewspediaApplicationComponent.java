package com.dariand.newspedia;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Component(modules = {AndroidInjectionModule.class, NewspediaApplicationModule.class})
public interface NewspediaApplicationComponent extends AndroidInjector<NewspediaApplication> {
}
