package au.com.translatorss.dao;

import au.com.translatorss.bean.AmazonFilePhoto;
import au.com.translatorss.bean.User;

public interface AmazonFilePhotoDao extends GenericDao<AmazonFilePhoto, Long>{

    AmazonFilePhoto savePhoto(AmazonFilePhoto amazonFilePhoto);

    AmazonFilePhoto getAmazonFilePhotoByUserId(User id);
}
