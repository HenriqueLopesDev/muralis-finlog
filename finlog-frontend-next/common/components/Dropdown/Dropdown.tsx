import React from 'react'

import { Menu } from '@mui/material'
import { DropdownRootProps } from './DropdownProps'
import { MenuPaperStyles } from './Dropdown.style'
import { DropdownItem } from './components/DropdownItem'

export const DropdownContext = React.createContext<{
  closeDropdown: () => void
} | null>(null)

function DropdownRoot({
  children,
  menuPosition = { vertical: 'top', horizontal: 'right' },
  menuAnimationDirection = { vertical: 'top', horizontal: 'left' },
  ContainerStyles = {},
  triggerContent,
  triggerStyles = {},
}: DropdownRootProps) {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null)
  const open = Boolean(anchorEl)
  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget)
  }
  const handleClose = () => {
    setAnchorEl(null)
  }

  return (
    <div>
      <button
        id="demo-positioned-button"
        aria-controls={open ? 'demo-positioned-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open ? 'true' : undefined}
        onClick={handleClick}
        className="mx-auto hover:cursor-pointer flex  items-center justify-center rounded-[8px] border border-[var(--color-01)] px-2 py-[0.475rem] text-[#1A1A1A] duration-200 hover:bg-[var(--color-02)] hover:text-white"
        style={triggerStyles}
      >
        {triggerContent}
      </button>
      <Menu
        id="demo-positioned-menu"
        aria-labelledby="demo-positioned-button"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        anchorOrigin={menuPosition}
        transformOrigin={menuAnimationDirection}
        slotProps={{
          list: {
            sx: {
              padding: '0.625rem',
              ...ContainerStyles,
            },
          },
          paper: {
            sx: {
              ...MenuPaperStyles,
              marginLeft: '-0.5rem',
            },
          },
        }}
      >
        <DropdownContext.Provider value={{ closeDropdown: handleClose }}>
          {children}
        </DropdownContext.Provider>
      </Menu>
    </div>
  )
}

/**
 *
 * @namespace Dropdown
 * @property {React.FC<DropdownRootProps>} Root - The dropdown container component
 * @property {React.FC<DropdownItemProps>} Item - Interactive dropdown menu item
 *
 * @example
 * // Basic usage
 * <Dropdown.Root triggerContent="Actions">
 *   <Dropdown.Item onClickCallback={handleEdit}>Edit</Dropdown.Item>
 *   <Dropdown.Item onClickCallback={handleDelete}>Delete</Dropdown.Item>
 * </Dropdown.Root>
 */
export const Dropdown = {
  Root: DropdownRoot,
  Item: DropdownItem,
}
