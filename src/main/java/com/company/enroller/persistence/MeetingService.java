package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;
	Session session;

	public MeetingService() {
		connector = DatabaseConnector.getInstance();
		session = connector.getSession();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = this.session.createQuery(hql);
		return query.list();
	}
	
	public Meeting findById(long id) {
		
		Meeting meeting = (Meeting)this.session.get(Meeting.class, id);
		
		return  meeting;
		
	}
	
	public void add(Meeting meeting) {
		
		Transaction transaction = this.session.beginTransaction();
		session.save(meeting);
		transaction.commit();
	}
	
	public void delete(Meeting meeting) {
		
		Transaction transaction = this.session.beginTransaction();
		session.delete(meeting);
		transaction.commit();
	}
	
	public void updateMeeting(Meeting meeting) {
		
		Transaction transaction = this.session.beginTransaction();
		session.clear();
		session.update(meeting);
		transaction.commit();
	}
	

}
