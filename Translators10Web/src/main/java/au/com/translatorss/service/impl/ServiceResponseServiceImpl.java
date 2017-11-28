package au.com.translatorss.service.impl;

import au.com.translatorss.bean.AmazonFile;
import au.com.translatorss.bean.ServiceResponse;
import au.com.translatorss.bean.ServiceResponseFiles;
import au.com.translatorss.bean.User;
import au.com.translatorss.dao.AmazonFileDao;
import au.com.translatorss.dao.ServiceResponseDao;
import au.com.translatorss.dao.ServiceResponseFilesDao;
import au.com.translatorss.dao.ServiceResponseStatusDao;
import au.com.translatorss.enums.FileType;
import au.com.translatorss.service.AmazonService;
import au.com.translatorss.service.ServiceResponseService;
import au.com.translatorss.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceResponseServiceImpl implements ServiceResponseService {

    private static final Logger logger = LoggerFactory.getLogger(ServiceResponseServiceImpl.class);

    @Autowired
    private ServiceResponseDao serviceResponseDao;

    @Autowired
    private ServiceResponseFilesDao serviceResponseFilesDao;

    @Autowired
    private ServiceResponseStatusDao serviceResponseStatusDao;

    @Autowired
    private AmazonService amazonService;

    @Autowired
    private UserService userService;

    @Autowired
    private AmazonFileDao amazonFileDao;

    @Override
    public ServiceResponse find(long key) {
        return serviceResponseDao.find(key);
    }

    @Override
    public void saveOrUpdate(ServiceResponse entity) {
        entity.setServiceResponseStatus(serviceResponseStatusDao.find(1));
        serviceResponseDao.saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdate(ServiceResponseFiles serviceResponseFile) {
        serviceResponseFilesDao.saveOrUpdate(serviceResponseFile);
    }

    @Override
    public ServiceResponse getServiceResponseByConversationId(Long conversationid) {
        return serviceResponseDao.getServiceResponseByConversationId(conversationid);
    }

    @Override
    public List<AmazonFile> saveFiles(ServiceResponse serviceResponse, List<MultipartFile> files) {
        List<AmazonFile> amazonFiles = new ArrayList<AmazonFile>();
        User user = userService.getCurrentUser();
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;

            try {
                amazonService.saveServiceResponseFile(serviceResponse,user,  file.getOriginalFilename(), file.getInputStream(), file.getContentType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return amazonFiles;
    }

    @Override
    public ServiceResponseFiles getServiceResponseById(long id) {
        return serviceResponseFilesDao.find(id);
    }

    @Override
    public void remove(ServiceResponse entity) throws Exception {
        serviceResponseDao.remove(entity);
    }

}
