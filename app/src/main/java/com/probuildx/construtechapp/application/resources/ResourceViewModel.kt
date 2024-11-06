package com.probuildx.construtechapp.application.resources

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.probuildx.construtechapp.domain.resources.Resource
import com.probuildx.construtechapp.infrastructure.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResourceViewModel(application: Application) : AndroidViewModel(application) {

    private val resourceDao = DatabaseProvider.getDatabase(application).resourceDao()

    fun addResource(resource: Resource) {
        viewModelScope.launch(Dispatchers.IO) {
            resourceDao.insertResource(resource)
        }
    }

    fun updateResource(resource: Resource) {
        viewModelScope.launch(Dispatchers.IO) {
            resourceDao.updateResource(resource)
        }
    }

    fun deleteResource(resource: Resource) {
        viewModelScope.launch(Dispatchers.IO) {
            resourceDao.deleteResource(resource)
        }
    }

    fun getResourceById(resourceId: String, callback: (Resource?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val resource = resourceDao.getResourceById(resourceId)
            callback(resource)
        }
    }

    fun getAllResources(callback: (List<Resource>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val resources = resourceDao.getAllResources()
            callback(resources)
        }
    }
}
