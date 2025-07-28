package com.elokator.repository;

import com.elokator.model.annoumcement.AnnouncementModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Repository
public class AnnouncementRepository {
    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementRepository.class);

    @PersistenceContext
    private EntityManager entityManager;


    public Integer createAnnouncement(final AnnouncementModel announcementModel) {
        final String query = "INSERT INTO announcement (title, description, author, customer_id, publication_date, customer_role) " +
                "VALUES (:title, :description, :author, :customerId, :publicationDate, :customerRole)";

        entityManager.createNativeQuery(query)
                .setParameter("title", announcementModel.getTitle())
                .setParameter("description", announcementModel.getDescription())
                .setParameter("author", announcementModel.getAuthor())
                .setParameter("customerId", announcementModel.getCustomerId())
                .setParameter("publicationDate", announcementModel.getPublicationDate())
                .setParameter("customerRole", announcementModel.getCustomerRole().getRoleName())
                .executeUpdate();

        final String queryGetCustomerId = "SELECT announcement_id FROM announcement ORDER BY announcement_id DESC LIMIT 1;";
        final Integer announcementId = (Integer) entityManager.createNativeQuery(queryGetCustomerId)
                .getSingleResult();

        LOG.info("Created announcement with id {}", announcementId);
        return announcementId;
    }

    public List<AnnouncementModel> getAllPublicAnnouncements(final String limit,
                                                             final String offset,
                                                             final LocalDateTime formattedDateFrom,
                                                             final LocalDateTime formattedDateTo) {
        return Collections.emptyList();
    }

    public List<AnnouncementModel> getAllAnnouncements(final String limit,
                                                       final String offset,
                                                       final Integer customerId,
                                                       final LocalDateTime formattedDateFrom,
                                                       final LocalDateTime formattedDateTo) {
        return Collections.emptyList();
    }

    public AnnouncementModel getAnnouncementById(final Integer announcementId) {
        final String query = "SELECT title, description, author, customer_id, publication_date, customer_role from announcement where announcement_id = :announcementId";
        try {
            final Object[] result = (Object[]) entityManager.createNativeQuery(query)
                    .setParameter("announcementId", announcementId)
                    .getSingleResult();


        } catch (NoResultException e) {
            LOG.warn("No announcement with id {}", announcementId);
            return null;
        }
        return null;
    }

}
