package com.es.sessionsecurity.service

import com.es.sessionsecurity.model.Session
import com.es.sessionsecurity.repository.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SessionService {

    @Autowired
    private lateinit var sessionRepository: SessionRepository

    fun checkToken(token: String?) : Boolean {

        // Comporbar nulable
        if (token == null) {throw Exception("Token cannot be null")}


        // Obtenemos la session asociada al token de la bbdd
        val s: Session = sessionRepository.findByToken(token).orElseThrow { RuntimeException("Token invalid") }

        // Comprobamos que la fecha sea valida
        return s.fechaExp.isAfter(LocalDateTime.now())
    }

}