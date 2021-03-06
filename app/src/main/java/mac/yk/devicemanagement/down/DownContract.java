package mac.yk.devicemanagement.down;

import android.content.Intent;

import java.io.File;
import java.util.ArrayList;

import mac.yk.devicemanagement.BasePresenter;
import mac.yk.devicemanagement.BaseView;
import mac.yk.devicemanagement.bean.FileEntry;
import mac.yk.devicemanagement.db.dbFile;

/**
 * Created by mac-yk on 2017/5/25.
 */

public interface DownContract {
    interface View extends BaseView<Presenter>{
        void refreshView();
//        void setUnEditStatus(AttachmentViewHolder holder);
//        void setEditStatus(AttachmentViewHolder holder);
        void showCustomDialog();
        void dismissCustomDialog();
        void toastException();
        void toastString(String s);
        void choseFile(Intent intent);
        void setEntries(ArrayList<FileEntry> entries);
        void startService(FileEntry entry);
        void showDialog(FileEntry entry,File file);

        void updateItem(FileEntry entry);
        void setUnMark();
    }
    interface Presenter extends BasePresenter{
        /**
         * 调用apiWrapper
         */

        void getAttachments();

        void deleteAttachment(FileEntry entry,boolean isUpdate, File file);
        void updateAttachment(FileEntry entry, String text);

        /**
         * 调用service
          */
        void filterFile(File file);
        void updateFile(FileEntry entry,File file);
        void uploadFile(File file);
        void uploadFile(FileEntry entry);
        void downloadFile(FileEntry entry);
        void downloadFiles(ArrayList<FileEntry> entries);
        /**
         * 调用db
         */
        void insertEntry(FileEntry fileEntry);
        void updateEntry(FileEntry fileEntry);
        void selectEntry(String name);
        void deleteEntry(String name);


        /**
         * 数据回调
         */
        void cancelDownload(FileEntry entry);
        void stopUpload(String name);
        void cancelUpload(FileEntry entry);
        void refreshView();
        void setMemory(FileEntry entry);
        void updateItem(FileEntry entry);
        dbFile getDbEntry();
    }
}
