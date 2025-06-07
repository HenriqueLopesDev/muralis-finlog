import React from 'react'

import { MenuItem } from '@mui/material'
import { DropdownContext } from '../Dropdown'
import { DropdownItemProps } from './DropdownItemProps'
import { dropdownItemStyles } from './DropdownItem.style'

export function DropdownItem({ children, onClickCallback }: DropdownItemProps) {
  const dropdownContext = React.useContext(DropdownContext)

  const handleOnClick = () => {
    onClickCallback()
    dropdownContext?.closeDropdown()
  }

  return (
    <MenuItem
      sx={{
        ...dropdownItemStyles,
      }}
      onClick={handleOnClick}
    >
      {children}
    </MenuItem>
  )
}
