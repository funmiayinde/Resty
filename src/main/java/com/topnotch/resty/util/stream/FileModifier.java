package com.topnotch.resty.util.stream;

import com.topnotch.resty.Constant.RestyConstant;
import com.topnotch.resty.log.Logger;

import java.io.File;
import java.io.IOException;

/**
 * @author funmiayinde
 */
public abstract class FileModifier {

    public final static FileModifier FILE_MODIFIER;
    private final static Logger logger = Logger.getLogger(FileModifier.class);

    static {
        FileModifier fileModifier = null;

        if (RestyConstant.fileModifier != null) {
            fileModifier = new DefaultFileModifier();

        } else {
            try {
                Class modifyClass = Class.forName(RestyConstant.fileModifier);
                fileModifier = (FileModifier) modifyClass.newInstance();
            } catch (ClassNotFoundException e) {
                logger.e("Could not found FileModifier.Class:", e);
            } catch (InstantiationException e) {
                logger.e("Could not instantiate FileModifier.class:", e);
            } catch (IllegalAccessException e) {
                logger.e("Could not access the file: ", e);
            }
        }
        FILE_MODIFIER = fileModifier;
    }


    /**
     * Return a File object holding a new name for the specified file
     */
    public abstract File modify(File f);

    protected boolean createdFile(File file) {
        try{
            return file.createNewFile();
        }catch (IOException ignored) {
            return false;
        }
    }
}
