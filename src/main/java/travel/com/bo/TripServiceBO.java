package travel.com.bo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.*;
import java.io.File;

import travel.com.service.*;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

public class TripServiceBO implements TripService
{

	@Autowired
	@Qualifier("tripDAO")
	TripDAO tripDAO;

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	@Autowired
	@Qualifier("utilities")
	Utilities utilities;

	@Autowired
	@Qualifier("itenaryDAO")
	ItenaryDAO itenaryDAO;

	@Autowired
	@Qualifier("tripImageDAO")
	TripImageDAO tripImageDAO;

	@Autowired
	@Qualifier("imageProcess")
	ImageProcess imageProcess;

	public static final int ONE_MB = 1024 * 1024; // 1 MB - Do not modify this.
	public static final int MAX_BUFFER_SIZE = ONE_MB; // 1 MB
	public static final int MAX_VIDEO_SIZE = 50 * ONE_MB; // 10 MB
	public static final int MAX_IMAGE_SIZE = 2 * ONE_MB; // 10 MB

	public Long addTripDetails(Trip trip) throws Exception
	{
		return tripDAO.addTripDetails(trip);
	}

	public
			Map<Object, Object> uploadTripDetailsImage(
					HttpServletRequest request) throws Exception
	{
		String UpdateVideoResult = "";
		FileOutputStream fileOutputStream = null;
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<String> list = new ArrayList<String>();
		String tempFileName = "";
		try
		{
			List<FileItem> items =
					new ServletFileUpload(new DiskFileItemFactory())
							.parseRequest(request);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart)
			{
				File file;
				for (FileItem item : items)
				{
					if (!item.isFormField())
					{
						String fileName = item.getName();
						String oldFileName = "";
						boolean isInMemory = item.isInMemory();
						long sizeInBytes = item.getSize();
						String finalImageURL = "";
						if (fileName.lastIndexOf("\\") >= 0)
						{

							oldFileName =
									fileName.substring(fileName
											.lastIndexOf("\\"));
							tempFileName =
									appProp.getUploadImagePath() + oldFileName;
							file = new File(tempFileName);
						} else
						{
							oldFileName =
									fileName.substring(fileName
											.lastIndexOf("\\") + 1);
							tempFileName =
									appProp.getUploadImagePath() + oldFileName;
							file = new File(tempFileName);
						}
						item.write(file);
						finalImageURL =
								utilities.getFiveDigitRandomNo()
										+ (oldFileName.toLowerCase());
						String newfile =
								appProp.getUploadImagePath() + finalImageURL;
						File newFile = new File(newfile);

						if (file.renameTo(newFile))
						{
							imageProcess.convertImageCompress(newfile,
									finalImageURL);
						} else
						{
							throw new ConstException(
									ConstException.ERR_CODE_FILE_RENAME,
									ConstException.ERR_MSG_FILE_RENAME);
						}
						list.add(finalImageURL);
					} else
					{
						map.put(item.getFieldName(), item.getString());
					}
				}
				String URLList = "";
				for (int i = 0; i < list.size(); i++)
				{
					URLList = URLList + list.get(i) + ",";
				}
				map.put("urls", URLList);
			} else
			{
				throw new ConstException(
						ConstException.ERR_CODE_MULTIPART_FORM_DATA,
						ConstException.ERR_MSG_MULTIPART_FORM_DATA);
			}
		} catch (Exception ex)
		{
			throw ex;
		}
		return map;
	}

	public void addItenary(List<Itenary> itenaryList) throws Exception
	{
		itenaryDAO.addItenary(itenaryList);
	}

	public Map<String, Object> getTripDetails(Trip trip) throws Exception
	{
		return tripDAO.getTripDetails(trip);
	}

	public void addTripImage(TripImage tripImage) throws Exception
	{
		tripImageDAO.addTripImage(tripImage);
	}

	public List<Trip> getUpdatingTripDetailsBasedId(Trip trip) throws Exception
	{
		return tripDAO.getUpdatingTripDetailsBasedId(trip);
	}

	public void deleteItenary(Long tripId) throws Exception
	{
		itenaryDAO.deleteItenary(tripId);
	}

	public void update(Trip trip) throws Exception
	{
		tripDAO.update(trip);
	}

	public List<TripImage> getTripImages(TripImage tripImage) throws Exception
	{
		return tripImageDAO.getTripImages(tripImage);
	}

	public void deleteTripImage(TripImage tripImage) throws Exception
	{
		tripImageDAO.deleteTripImage(tripImage);
	}

	public void updateTripDetailStatus(Trip trip) throws Exception
	{
		tripDAO.updateTripDetailStatus(trip);
	}

	public List<Trip> getFilterTripsDetails(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap, int START_INDEX, int END_INDEX)
	{
		return tripDAO.getFilterTripsDetails(tripTable, activityTable, status,
				priceMap, START_INDEX, END_INDEX);

	}

	public int getFilterTripsDetailsnumEntries(Map<String, Object> tripTable,
			Map<String, Object> activityTable, String status,
			Map<String, Object> priceMap)
	{
		return tripDAO.getFilterTripsDetailsnumEntries(tripTable,
				activityTable, status, priceMap);
	}

	public List<Trip> getTripDetails_Users(Trip trip) throws Exception
	{
		return tripDAO.getTripDetails_Users(trip);
	}
	public int getAllValidTripDetailsCount(Trip trip) throws Exception
	{
		return tripDAO.getAllValidTripDetailsCount(trip);
	}

	public List<Trip> getTripDetailsBasedId(Trip trip) throws Exception
	{
		return tripDAO.getTripDetailsBasedId(trip);
	}

}
