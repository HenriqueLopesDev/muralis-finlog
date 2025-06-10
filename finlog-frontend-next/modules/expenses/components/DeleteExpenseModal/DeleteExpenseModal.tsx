import * as React from 'react'
import Dialog from '@mui/material/Dialog'
import DialogActions from '@mui/material/DialogActions'
import DialogContent from '@mui/material/DialogContent'
import DialogTitle from '@mui/material/DialogTitle'
import CloseIcon from '@mui/icons-material/Close'
import { IconButton } from '@mui/material'
import { DeleteExpenseModalProps } from './DeleteExpenseModalProps'

export function DeleteExpenseModal({
  open,
  setOpen,
  callBackFn,
}: DeleteExpenseModalProps) {
  const handleClose = () => {
    setOpen({
      expenseId: 0,
      open: false,
    })
  }

  const executeCallBackAndClose = () => {
    callBackFn()
    handleClose()
  }

  return (
    <React.Fragment>
      <Dialog
        fullWidth={true}
        maxWidth={'xs'}
        open={open.open}
        onClose={handleClose}
        closeAfterTransition={false}
      >
        <DialogTitle sx={{ fontFamily: 'var(--font-montserrat)' }}>
          Excluir despesa
        </DialogTitle>
        <IconButton
          aria-label="close"
          onClick={handleClose}
          sx={(theme) => ({
            position: 'absolute',
            right: 8,
            top: 8,
            color: theme.palette.grey[500],
          })}
        >
          <CloseIcon />
        </IconButton>
        <DialogContent dividers>
          <p className="text-center font-poppins">
            Deseja realmente excluir essa despesa? Atenção, essa ação é{' '}
            <strong>irreversível</strong>.
          </p>
        </DialogContent>
        <DialogActions sx={{ justifyContent: 'space-between' }}>
          <button
            className="h-10 border border-[var(--color-01)] hover:cursor-pointer flex items-center justify-center gap-1 rounded-[10px] bg-transparent px-3 py-2 font-poppins font-semibold text-[var(--color-01)] transition-colors duration-200 hover:bg-[var(--color-02)] hover:text-white"
            onClick={handleClose}
          >
            Cancelar
          </button>
          <button
            className="hover:cursor-pointer flex items-center justify-center gap-1 rounded-[10px] px-3 py-2 font-poppins font-semibold text-white transition-colors duration-200 bg-red-700 hover:bg-red-800"
            onClick={executeCallBackAndClose}
          >
            Excluir
          </button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  )
}
