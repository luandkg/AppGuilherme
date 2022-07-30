package com.luandkg.guilherme.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import com.luandkg.guilherme.libs.tempo.Data;

import java.util.Calendar;

public class CaixaDeDialogoSelecionarData {

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public static Button BTN_DATA;
        public static Data DATA_SELECIONADA;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();

            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, DATA_SELECIONADA.getAno(), DATA_SELECIONADA.getMes() - 1, DATA_SELECIONADA.getDia());
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            DATA_SELECIONADA.set(year, month + 1, day);
            BTN_DATA.setText(DATA_SELECIONADA.getFluxo());

        }
    }

}

