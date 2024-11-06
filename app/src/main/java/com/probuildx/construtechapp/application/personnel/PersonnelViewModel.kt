package com.probuildx.construtechapp.application.personnel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.probuildx.construtechapp.domain.personnel.Worker
import com.probuildx.construtechapp.domain.personnel.Team
import com.probuildx.construtechapp.infrastructure.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonnelViewModel(application: Application) : AndroidViewModel(application) {

    private val personnelDao = DatabaseProvider.getDatabase(application).personnelDao()

    // Funciones CRUD para Worker
    fun addWorker(worker: Worker) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelDao.insertWorker(worker)
        }
    }

    fun updateWorker(worker: Worker) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelDao.updateWorker(worker)
        }
    }

    fun deleteWorker(worker: Worker) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelDao.deleteWorker(worker)
        }
    }

    fun getWorkerById(workerId: String, callback: (Worker?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val worker = personnelDao.getWorkerById(workerId)
            callback(worker)
        }
    }

    fun getAllWorkers(callback: (List<Worker>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val workers = personnelDao.getAllWorkers()
            callback(workers)
        }
    }

    // Funciones CRUD para Team
    fun addTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelDao.insertTeam(team)
        }
    }

    fun updateTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelDao.updateTeam(team)
        }
    }

    fun deleteTeam(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelDao.deleteTeam(team)
        }
    }

    fun getTeamById(teamId: String, callback: (Team?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val team = personnelDao.getTeamById(teamId)
            callback(team)
        }
    }

    fun getAllTeams(callback: (List<Team>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val teams = personnelDao.getAllTeams()
            callback(teams)
        }
    }
}
