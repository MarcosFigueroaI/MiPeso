package com.mf.mipeso.data;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mf.mipeso.model.Peso;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PesoDao_Impl implements PesoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Peso> __insertionAdapterOfPeso;

  private final EntityDeletionOrUpdateAdapter<Peso> __deletionAdapterOfPeso;

  private final EntityDeletionOrUpdateAdapter<Peso> __updateAdapterOfPeso;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPesos;

  public PesoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPeso = new EntityInsertionAdapter<Peso>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `peso_table` (`id`,`dia`,`fecha`,`peso`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Peso value) {
        stmt.bindLong(1, value.getId());
        if (value.getDia() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDia());
        }
        if (value.getFecha() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFecha());
        }
        stmt.bindDouble(4, value.getPeso());
      }
    };
    this.__deletionAdapterOfPeso = new EntityDeletionOrUpdateAdapter<Peso>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `peso_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Peso value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPeso = new EntityDeletionOrUpdateAdapter<Peso>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `peso_table` SET `id` = ?,`dia` = ?,`fecha` = ?,`peso` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Peso value) {
        stmt.bindLong(1, value.getId());
        if (value.getDia() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDia());
        }
        if (value.getFecha() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFecha());
        }
        stmt.bindDouble(4, value.getPeso());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAllPesos = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from peso_table";
        return _query;
      }
    };
  }

  @Override
  public Object addPeso(final Peso peso, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPeso.insert(peso);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deletePeso(final Peso peso, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPeso.handle(peso);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object updatePeso(final Peso peso, final Continuation<? super Unit> p1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPeso.handle(peso);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, p1);
  }

  @Override
  public Object deleteAllPesos(final Continuation<? super Unit> p0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPesos.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllPesos.release(_stmt);
        }
      }
    }, p0);
  }

  @Override
  public LiveData<List<Peso>> getPeso() {
    final String _sql = "select * from peso_table order by id desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"peso_table"}, false, new Callable<List<Peso>>() {
      @Override
      public List<Peso> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDia = CursorUtil.getColumnIndexOrThrow(_cursor, "dia");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfPeso = CursorUtil.getColumnIndexOrThrow(_cursor, "peso");
          final List<Peso> _result = new ArrayList<Peso>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Peso _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDia;
            _tmpDia = _cursor.getString(_cursorIndexOfDia);
            final String _tmpFecha;
            _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
            final float _tmpPeso;
            _tmpPeso = _cursor.getFloat(_cursorIndexOfPeso);
            _item = new Peso(_tmpId,_tmpDia,_tmpFecha,_tmpPeso);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
