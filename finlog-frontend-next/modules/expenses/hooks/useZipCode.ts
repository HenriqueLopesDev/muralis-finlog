import {
  formatBrazilianZipCode,
  removeNonNumericCharacters,
} from '@/common/utils/formatters'
import { useLazyGetAddressByZipCodeQuery } from '@/modules/addresses/lib/redux/AdressesApiSlice'
import { Address } from '@/modules/addresses/types/Address'
import { UseFormSetValue } from 'react-hook-form'
import { CreateExpenseFormData } from '../validation/createExpenseValidationSchema'
import React from 'react'
import { ActionAlert } from '@/common/utils/ActionAlert'

export const useZipCode = (
  setValue: UseFormSetValue<CreateExpenseFormData>,
) => {
  const [addressLockedFields, setAddressLockedFields] = React.useState({
    street: false,
    neighborhood: false,
    city: false,
    state: false,
    complement: false,
  })
  const [fetchAddressByZipCode] = useLazyGetAddressByZipCodeQuery()

  const setAllAddressFieldsLocked = (locked: boolean) => {
    setAddressLockedFields({
      street: locked,
      neighborhood: locked,
      city: locked,
      state: locked,
      complement: locked,
    })
  }

  const fillAddressFields = (address: Address) => {
    const updatedLocks = {
      street: false,
      neighborhood: false,
      city: false,
      state: false,
      complement: false,
    }

    if (address.street) {
      setValue('street', address.street)
      updatedLocks.street = true
    }

    if (address.neighborhood) {
      setValue('neighborhood', address.neighborhood)
      updatedLocks.neighborhood = true
    }

    if (address.city) {
      setValue('city', address.city)
      updatedLocks.city = true
    }

    if (address.state) {
      setValue('state', address.state)
      updatedLocks.state = true
    }

    if (address.complement) {
      setValue('complement', address.complement)
      updatedLocks.complement = true
    }

    setAddressLockedFields(updatedLocks)
  }

  const clearAddressFields = () => {
    setValue('street', '')
    setValue('neighborhood', '')
    setValue('city', '')
    setValue('state', '')
    setValue('complement', '')
  }

  const handleZipCodeChange = async (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const formattedValue = formatBrazilianZipCode(event.target.value)
    event.target.value = formattedValue

    if (formattedValue.length === 0) {
      clearAddressFields()
      setAllAddressFieldsLocked(false)
      return
    }

    if (formattedValue.length < 9) return

    const { error, data: foundAddress } = await fetchAddressByZipCode(
      removeNonNumericCharacters(formattedValue),
    )

    if (error) {
      setAllAddressFieldsLocked(false)
      return ActionAlert.show({
        icon: 'error',
        title: 'CEP não encontrado, insira os dados manualmente.',
      })
    }

    if (foundAddress) {
      setAllAddressFieldsLocked(true)
      fillAddressFields(foundAddress)
    }

    return formattedValue
  }

  return {
    addressLockedFields,
    setAllAddressFieldsLocked,
    handleZipCodeChange,
  }
}
