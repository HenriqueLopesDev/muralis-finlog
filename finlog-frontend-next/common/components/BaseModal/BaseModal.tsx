import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  IconButton,
} from '@mui/material'
import CloseIcon from '@mui/icons-material/Close'

interface BaseModalProps {
  open: boolean
  onClose: () => void
  maxWidth?: 'xs' | 'sm' | 'md' | 'lg' | 'xl'
  title: React.ReactNode
  children: React.ReactNode
  actions?: React.ReactNode
}

export function BaseModal({
  children,
  onClose,
  open,
  title,
  actions,
  maxWidth,
}: BaseModalProps) {
  return (
    <Dialog
      fullWidth
      maxWidth={maxWidth}
      open={open}
      onClose={onClose}
      closeAfterTransition={false}
      scroll="body"
    >
      <DialogTitle
        sx={{
          fontFamily: 'var(--font-montserrat)',
          position: 'relative',
          paddingRight: 5,
        }}
      >
        {title}
        <IconButton
          aria-label="close"
          onClick={onClose}
          sx={{
            position: 'absolute',
            right: 8,
            top: 8,
            color: (theme) => theme.palette.grey[500],
          }}
        >
          <CloseIcon />
        </IconButton>
      </DialogTitle>
      <DialogContent dividers>{children}</DialogContent>
      {actions && (
        <DialogActions sx={{ justifyContent: 'space-between' }}>
          {actions}
        </DialogActions>
      )}
    </Dialog>
  )
}
