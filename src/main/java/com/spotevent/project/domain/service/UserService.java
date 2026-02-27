package com.spotevent.project.domain.service;

import com.spotevent.project.domain.model.UserModel;

import java.util.List;
import java.util.Set;

public interface UserService {
    // Récupère l'ID à partir du login (pour les Use Cases)
    Integer getUserIdByLogin(String login);

    // Récupère un objet "Profil" pour le mapping des Responses
    UserModel getUserById(Integer id);

    // Récupère une liste de profils (Batch loading pour les performances)
    List<UserModel> getUsersByIds(Set<Integer> ids);
}
