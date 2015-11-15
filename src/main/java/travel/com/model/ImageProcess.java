package travel.com.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.apache.log4j.Logger;

import travel.com.service.*;
import travel.com.dao.*;
import travel.com.model.*;
import travel.com.util.*;

public class ImageProcess
{

	private static final Logger logger = Logger.getLogger(ImageProcess.class.getName());

	@Autowired
	@Qualifier("appProp")
	AppProp appProp;

	public void convertImageCompress(String filePath, String fileName)
	{
		String imgUrl = null;
		byte[] imageInByte = null;
		String IMG_EXTENSION = "";
		try
		{
			String compressPath = appProp.getUploadImageCompressPath();
			BufferedImage originalImage = ImageIO.read(new File(filePath));
			IMG_EXTENSION = getFileExtension(fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, IMG_EXTENSION, baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
			InputStream in = new ByteArrayInputStream(imageInByte);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, IMG_EXTENSION, new File(
					compressPath + fileName));

		}
		catch (Exception ex)
		{
			logger.error("convertImageCompress :: " + ex.getMessage());
		}
	}

	private String getFileExtension(String fileNames)
	{
		File file = new File(fileNames);
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}
}
